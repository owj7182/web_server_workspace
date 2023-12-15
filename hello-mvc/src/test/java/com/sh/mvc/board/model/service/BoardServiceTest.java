package com.sh.mvc.board.model.service;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.member.model.service.MemberService;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardServiceTest {
    BoardService boardService;

    @BeforeEach
    public void beforeEach() {
        this.boardService = new BoardService();
    }

    @DisplayName("BoardService객체는 null이 아니다.")
    @Test
    public void test1() {
        assertThat(boardService).isNotNull();
    }

    @DisplayName("게시글 전체 조회")
    @Test
    public void test2() {
        List<Board> boards = boardService.findAll();
        assertThat(boards).isNotNull().isNotEmpty();

        boards.forEach((board) -> {
            System.out.println(board);
            assertThat(board.getId()).isNotEqualTo(0);
            assertThat(board.getMemberId()).isNotNull();
        });
    }

    @DisplayName("게시글 한건 조회")
    @Test
    public void test3() {
        Board board = boardService.findById(1);
        System.out.println(board);

        assertThat(board).isNotNull();

        assertThat(board.getId()).isNotNull();
        assertThat(board.getMemberId()).isNotNull();
    }

    @Order(1)
    @DisplayName("게시글 한건 추가")
    @Test
    public void test4() {
        long id = 61;
        String memberId = "kamsayoyo";

        Board board = new Board(id, "안녕하세요, 게시판입니다 - 61", memberId, "안녕하세요", 0, null);

        int result = boardService.insertBoard(board);
        assertThat(result).isEqualTo(1);

        Board board2 = boardService.findById(id);
        assertThat(board2).isNotNull();
        assertThat(board2.getId()).isNotNull();
        assertThat(board2.getMemberId()).isNotNull();
    }

    @Order(2)
    @DisplayName("게시글 수정")
    @Test
    public void test5() {
        long id = 61;
        Board board = boardService.findById(id);

        String newTitle = board.getTitle() + "(수정)";
        String newContent = "반갑습니다";

        board.setTitle(newTitle);
        board.setContent(newContent);

        int result = boardService.updateBoard(board);
        assertThat(result).isGreaterThan(0);

        Board board2 = boardService.findById(id);
        assertThat(board2.getTitle()).isEqualTo(newTitle);
        assertThat(board2.getContent()).isEqualTo(newContent);

    }

    @Order(3)
    @DisplayName("게시글 삭제")
    @Test
    public void test6() {
        long id = 61;
        Board board = boardService.findById(id);
        assertThat(board).isNotNull();

        int result = boardService.deleteBoard(id);
        assertThat(result).isGreaterThan(0);

        Board board2 = boardService.findById(id);
        assertThat(board2).isNull();
    }

}
