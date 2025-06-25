import dao.*;
import entity.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AdviceMain {
    public static void main(String[] args) {
        try (Connection conn = connectDB.getConnection()) {
            if (conn == null) return;

            Scanner scanner = new Scanner(System.in);

            UserDAO userDAO = new UserDAOImpl(conn);
            PostDAO postDAO = new PostDAOImpl(conn);
            CommentDAO commentDAO = new CommentDAOImpl(conn);

            User currentUser = null;  // 현재 로그인한 사용자 정보

            while (true) {
                if (currentUser == null) {
                    // 비로그인 상태 메뉴
                    showLoginMenu();
                    String input = scanner.nextLine();

                    switch (input) {
                        case "1": // 회원가입
                            System.out.print("닉네임 입력: ");
                            String nickname = scanner.nextLine().trim();
                            if (nickname.isEmpty()) {
                                System.out.println("\u001B[닉네임은 빈 칸일 수 없습니다.\u001B[0m");

                                break;
                            }
                            if (userDAO.getUserByNickname(nickname) != null) {
                                System.out.println("\u001B[31m 이미 존재하는 닉네임입니다.\u001B[0m");
                                break;
                            }
                            User newUser = new User();
                            newUser.setNickname(nickname);
                            userDAO.insertUser(newUser);
                            System.out.println("\u001B[32m 회원가입 완료! 회원번호(ID): " + newUser.getId() + "\u001B[0m");
                            currentUser = newUser;  // 가입과 동시에 로그인 처리
                            break;

                        case "2": // 로그인
                            System.out.print("닉네임 입력: ");
                            String loginNick = scanner.nextLine().trim();
                            User loginUser = userDAO.getUserByNickname(loginNick);
                            if (loginUser == null) {
                                System.out.println("\u001B[31m 존재하지 않는 닉네임입니다.\u001B[0m");
                            } else {
                                currentUser = loginUser;
                                System.out.println("\u001B[32m 로그인 성공! " + currentUser.getNickname() + "님 환영합니다.\u001B[0m");
                            }
                            break;

                        case "0":
                            System.out.println("프로그램 종료");
                            return;

                        default:
                            System.out.println("\u001B[31m 잘못된 입력입니다. 다시 시도하세요.\n\u001B[0m");
                    }

                } else {
                    // 로그인 상태 메뉴
                    showMainMenu();
                    String input = scanner.nextLine();

                    switch (input) {
                        case "1": // 고민 작성
                            System.out.print("제목 입력: ");
                            String title = scanner.nextLine();
                            System.out.print("내용 입력: ");
                            String content = scanner.nextLine();

                            Post newPost = new Post();
                            newPost.setUserId(currentUser.getId());  // 로그인한 회원 ID 사용
                            newPost.setTitle(title);
                            newPost.setContent(content);
                            newPost.setLikeCount(0);
                            newPost.setCreatedAt(LocalDateTime.now());
                            postDAO.insertPost(newPost);

                            System.out.println("\u001B[32m 고민이 등록되었습니다.\n\u001B[0m");
                            break;

                        case "2": // 고민 목록 보기
                            List<Post> posts = postDAO.getAllPosts();
                            for (Post p : posts) {
                                System.out.printf("[%d] 제목: %s   (공감: %d)\n", p.getId(), p.getTitle(), p.getLikeCount());
                            }
                            System.out.print("Enter 입력 시 메인 메뉴로 돌아갑니다.");
                            scanner.nextLine();
                            break;

                        case "3": // 고민 상세 보기
                            System.out.print("확인할 글 ID 입력: ");
                            int postId = Integer.parseInt(scanner.nextLine());
                            Post post = postDAO.getPostById(postId);

                            if (post != null) {
                                System.out.printf("\n=== 고민 상세 보기 (ID: %d) ===\n", post.getId());
                                System.out.println("제목: " + post.getTitle());
                                System.out.println("내용: " + post.getContent());
                                System.out.println("작성일: " + post.getCreatedAt());
                                System.out.println("공감 수: " + post.getLikeCount());
                                System.out.println("\n-- 댓글 목록 --");

                                List<Comment> comments = commentDAO.getAllComment(postId);
                                for (Comment c : comments) {
                                    System.out.printf("[%d] %s\n", c.getId(), c.getContent());
                                }

                                label:
                                while (true) {
                                    System.out.println("\n1. 댓글 달기   2. 공감하기   3. 댓글 삭제   0. 뒤로가기");
                                    System.out.print("번호 입력 > ");
                                    String subInput = scanner.nextLine();

                                    switch (subInput) {
                                        case "1":
                                            System.out.print("댓글 내용 입력: ");
                                            String commentContent = scanner.nextLine();

                                            Comment comment = new Comment();
                                            comment.setPostId(postId);
                                            comment.setUserId(currentUser.getId());  // 로그인한 회원 ID

                                            comment.setContent(commentContent);
                                            comment.setCreatedAt(LocalDateTime.now());
                                            commentDAO.insertComment(comment);

                                            System.out.println("\u001B[32m 댓글이 등록되었습니다.\u001B[0m");

                                            break;
                                        case "2":
                                            post.setLikeCount(post.getLikeCount() + 1);
                                            postDAO.updatePost(post);
                                            System.out.println("\u001B[35m❤️ 공감했습니다!\u001B[0m");

                                            break;
                                        case "3":
                                            System.out.print("삭제할 댓글 ID 입력: ");
                                            int cid = Integer.parseInt(scanner.nextLine());
                                            commentDAO.deleteComment(cid);
                                            System.out.println("댓글 삭제 완료");

                                            break;
                                        case "0":
                                            break label;
                                        default:
                                            System.out.println("\u001B[31m 잘못된 입력입니다.\u001B[0m");
                                            break;
                                    }
                                }

                            } else {
                                System.out.println("\\u001B[31m해당 고민이 존재하지 않습니다.\n");
                            }
                            break;

                        case "4": // 로그아웃
                            currentUser = null;
                            System.out.println("\u001B[32m 로그아웃 되었습니다.\n\u001B[0m");
                            break;

                        case "0":
                            System.out.println("프로그램 종료");
                            return;

                        default:
                            System.out.println("\u001B[31m 잘못된 입력입니다.\u001B[0m");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ DB 연결 실패");
            e.printStackTrace();
        }
    }

    public static void showLoginMenu() {
        System.out.println("====== 익명 고민 상담소 ======");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("0. 종료");
        System.out.print("번호 입력 > ");
    }

    public static void showMainMenu() {
        System.out.println("======== 익명 고민 상담소 ========");
        System.out.println("1. 고민 작성");
        System.out.println("2. 고민 목록 보기");
        System.out.println("3. 고민 상세 보기");
        System.out.println("4. 로그아웃");
        System.out.println("0. 종료");
        System.out.println("================================");
        System.out.print("번호 입력 > ");
    }
}
