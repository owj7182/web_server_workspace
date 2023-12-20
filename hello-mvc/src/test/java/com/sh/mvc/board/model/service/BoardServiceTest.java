package com.sh.mvc.board.model.service;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.vo.BoardVo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;

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

    @Disabled
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

    @Disabled
    @Order(1)
    @DisplayName("게시글 한건 추가")
    @Test
    public void test4() {
        long id = 61;
        String memberId = "kamsayoyo";

        BoardVo board = new BoardVo();
        board.setTitle("제목");
        board.setContent("내용");

        int result = boardService.insertBoard(board);
        assertThat(result).isEqualTo(1);

        Board board2 = boardService.findById(id);
        assertThat(board2).isNotNull();
        assertThat(board2.getId()).isNotNull();
        assertThat(board2.getMemberId()).isNotNull();
    }

    @Disabled
    @Order(2)
    @DisplayName("게시글 수정")
    @Test
    public void test5() {
        long id = 61;
        BoardVo board = boardService.findById(id);

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

    @Disabled
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

    @DisplayName("게시글은 10건씩 조회될 수 있어야 된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void test7(int page) {
        assertThat(page).isGreaterThan(0);
        System.out.println(page);

        int limit = 10;
        Map<String, Object> param = Map.of("page", page, "limit", limit);
        List<BoardVo> boards = boardService.findAll(param);

        System.out.println(boards);
        assertThat(boards).isNotNull();
        assertThat(boards.size()).isLessThanOrEqualTo(limit);
    }

    @DisplayName("전체 게시글 수가 정상 조회된다")
    @Test
    public void test8() {
        int totalCount = boardService.getTotalCount();
        System.out.println(totalCount);
        assertThat(totalCount).isGreaterThanOrEqualTo(0);
    }

}
