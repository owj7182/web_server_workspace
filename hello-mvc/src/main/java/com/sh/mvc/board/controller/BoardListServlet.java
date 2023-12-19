package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardVo;
import com.sh.mvc.common.HelloMvcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int page = 1;
        int limit = 10;
        try {
                page  = Integer.parseInt(req.getParameter("page"));
            } catch(NumberFormatException ignore) {}

        Map<String,Object> param = Map.of("page",page,"limit",limit);

        BoardService boardService = new BoardService();
        List<BoardVo> boards = boardService.findAll(param);
        req.setAttribute("boards", boards);
        System.out.println(boards);

        int totalCount = boardService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = HelloMvcUtils.getPagebar(page,limit,totalCount,url);
        req.setAttribute("pagebar",pagebar);
        req.setAttribute("boards",boards);
        System.out.println(boards);

        req.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(req, resp);
    }
}