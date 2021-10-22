<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- <fmt:parseDate var="parseDateValue" value="${regDate }"  pattern="yyyy.MM.dd HH:mm:ss" /> --%>
<%-- <fmt:parseDate var="parseRegDate" value="${ todo.regDate }" pattern="yyyy-MM-dd HH:MM:SS" /> --%>

<%-- <fmt:formatDate var="parseRegDate" value="${parseDateValue }" pattern="yyyy-MM-dd HH:mm:ss" /> --%>


<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board?pageno=1" method="post">
					<input type="text" id="kwd" name="kwd" value="${findkey}">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:set var='count' value='${fn:length(list) }' />
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<tr>
							<td>${count-status.index }</td>
							<c:choose>
								<c:when test="${vo.depth == 0}">
									
									<c:choose>
										<c:when test='${vo.hit != -1 }'>
											<td style="text-align:left; padding-left:0px"><a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }">${vo.title }</a></td>
										</c:when>
										<c:otherwise>
											<td style="text-align:left; padding-left:0px">${vo.title }</td>
										</c:otherwise>
									</c:choose>
									
									
								</c:when>
								
								<c:otherwise>
									<c:choose>
										<c:when test='${vo.hit != -1 }'>
											<td style="text-align:left; padding-left:${vo.depth * 20 }px"><img src='${pageContext.request.contextPath }/assets/images/reply.png' /><a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }">${vo.title }</a></td>
										</c:when>
										<c:otherwise>
											<td style="text-align:left; padding-left:${vo.depth * 20 }px"><img src='${pageContext.request.contextPath }/assets/images/reply.png' />${vo.title }</td>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
								
							</c:choose>
							
							<c:choose>
								<c:when test='${vo.hit != -1 }'>
									<td>${vo.userName }</td>
									<td>${vo.hit }</td>
									<td>${fn:substring(vo.regDate,0,19)}</td>
								</c:when>
								<c:otherwise>
									<td></td>
									<td></td>
									<td></td>
								</c:otherwise>
							</c:choose>
							
							<c:if test='${(vo.userNo == authUser.no) && (vo.hit != -1)}' >
								<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }" class="del">삭제</a></td>
							</c:if>
							<c:if test='${vo.userNo != authUser.no}' >
								<td></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
<!-- 				<div class="pager"> -->
<!-- 					<ul> -->
<!-- 						<li><a href="">◀</a></li> -->
<!-- 						<li><a href="">1</a></li> -->
<!-- 						<li class="selected">2</li> -->
<!-- 						<li><a href="">3</a></li> -->
<!-- 						<li>4</li> -->
<!-- 						<li>5</li> -->
<!-- 						<li><a href="">▶</a></li> -->
<!-- 					</ul> -->
<!-- 				</div>		 -->
		
		
				<div class="pager">
					<ul>
   						<c:if test='${param.pageno != page.firstPageNo}' >
					    	<li><a href="${pageContext.request.contextPath }/board?pageno=${page.firstPageNo}&kwd=${findkey}" class="first">◀</a></li>
					    	<li><a href="${pageContext.request.contextPath }/board?pageno=${page.prevPageNo}&kwd=${findkey}" class="prev">◁</a></li>
 					    </c:if>
					        <c:forEach var="i" begin="${page.startPageNo}" end="${page.endPageNo}" step="1">
					            <c:choose>
					                <c:when test="${i == param.pageno}">
					                	<li style='color:red'>${i}</li>
<%-- 					                	<li ><a href="${pageContext.request.contextPath }/board?pageno=${i}" class="choice" style='color:red'>${i}</a></li> --%>
					                </c:when>
					                <c:otherwise>
					                	<li><a href="${pageContext.request.contextPath }/board?pageno=${i}&kwd=${findkey}">${i}</a></li>
					                </c:otherwise>
					            </c:choose>
					        </c:forEach>
  						<c:if test='${param.pageno != page.finalPageNo}' >
						    <li><a href="${pageContext.request.contextPath }/board?pageno=${page.nextPageNo}&kwd=${findkey}" class="next">▷</a></li>
						    <li><a href="${pageContext.request.contextPath }/board?pageno=${page.finalPageNo}&kwd=${findkey}" class="last">▶</a></li>
						</c:if>
				    </ul>
				</div>

							
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?a=add" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>