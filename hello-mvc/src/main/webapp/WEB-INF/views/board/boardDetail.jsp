<%--
  Created by IntelliJ IDEA.
  User: sisna
  Date: 2023-12-19
  Time: 오후 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sh.mvc.member.model.entity.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="xl:container p-8">
    <div class="w-full p-6 bg-white border border-gray-200 rounded-lg shadow">
        <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">${fn:escapeXml(board.title)}</h5>
        <p class="mb-3 font-normal text-gray-500">${board.member.name}(${board.memberId})</p>
        <p class="mb-3 font-normal text-gray-700">${board.content}</p>
        <c:forEach items="${board.attachments}" var="attach">
            <a href="${pageContext.request.contextPath}/upload/board/${attach.renamedFilename}" download="${attach.originalFilename}" class="flex items-center text-blue-600 hover:underline">
                <img src="../images/file.png" class="w-[16px] mr-1">
                ${attach.originalFilename}
            </a>
        </c:forEach>
        <div class="text-sm mt-2 font-medium text-gray-400">
            조회수 <span>${board.readCount}</span>
        </div>
        <div class="text-sm mt-2 font-medium text-gray-400">
            작성일
            <span>
                <fmt:parseDate value="${board.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                <fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd HH:mm"/>
                ${board.regDate}
            </span>
        </div>
        <%-- 작성자 본인과 관리자에게만 노출 --%>
        <c:if test="${board.memberId eq loginMember.id || loginMember.role eq Role.A}">
            <div class="flex justify-end">
                <button
                        type="button"
                        onclick="location.href = '${pageContext.request.contextPath}/board/boardUpdate?id=${board.id}';"
                        class="px-5 py-2.5 mt-4 mr-4 sm:mt-6 text-sm font-medium text-center text-white bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200">
                    수정
                </button>
                <button type="button" class="px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-white bg-red-700 rounded-lg focus:ring-4 focus:ring-primary-200">
                    삭제
                </button>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
