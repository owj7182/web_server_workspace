package com.sh.ajax.json;

import com.google.gson.Gson;
import com.sh.ajax.celeb.model.entity.Celeb;
import com.sh.ajax.celeb.model.entity.Type;
import com.sh.ajax.celeb.model.service.CelebService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/json/celeb/update")
public class JsonCelebUpdateServlet extends HttpServlet {
    private CelebService celebService = new CelebService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. multipart/form-data처리
        // DiskFileItemFactory
        File repository = new File("C:\\Workspaces\\web_server_workspace\\ajax\\src\\main\\webapp\\images\\celeb");
        int sizeThreshold = 10 * 1024 * 1024; // 10mb
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);
        // ServletFileUpload
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        // 1. 사용자입력값 처리
        Celeb celeb = null;
        try {
            // FileItem
            Map<String, List<FileItem>> fileItemMap = servletFileUpload.parseParameterMap(req); // throw FileUploadException
            // 텍스트처리
            Long id = Long.parseLong(fileItemMap.get("id").get(0).getString("utf-8")); // List<FileItem> -> FileItem -> 실제값 -> long으로 변환
            String name = fileItemMap.get("name").get(0).getString("utf-8"); // List<FileItem> -> FileItem -> 실제값
            Type type = Type.valueOf(fileItemMap.get("type").get(0).getString("utf-8")); // List<FileItem> -> FileItem -> 실제값 -> enum으로 변환 

            // 기존값 조회 (기존 profile값 유지 목적)
            celeb = celebService.findById(id);
            celeb.setName(name);
            celeb.setType(type);
            // 기존파일 삭제처리
            List<FileItem> delProfileFileItem = fileItemMap.get("delProfile"); // 체크시 "on" 전송되고, FileItem객체가 생성된다. 체크하지 않은 경우, List<FileItem>은 null!
            if(delProfileFileItem != null) {
                celeb.setProfile(null); 
            }
            // 파일처리
            FileItem profileFileItem = fileItemMap.get("profile").get(0);
            if(profileFileItem.getSize() > 0) {
                // 파일명 가져오기
                String profile = profileFileItem.getName(); // 사용자가 업로드한 파일명
                celeb.setProfile(profile);
                // 파일 저장
                profileFileItem.write(new File(repository, profile)); // throw Exception
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(celeb);

        // 2. 업무로직
        int result = celebService.updateCeleb(celeb);

        // 3. 비동기 요청시 리다이렉트 없음. 적절한 json응답처리
        // 사용자메세징
        Map<String, Object> resultMap = Map.of("msg", "정상등록되었습니다. 😜");

        resp.setContentType("application/json; charset=utf-8");
        new Gson().toJson(resultMap, resp.getWriter());

    }
}



