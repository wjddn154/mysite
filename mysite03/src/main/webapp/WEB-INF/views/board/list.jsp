<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="post">
					<input type="text" id="kwd" name="kwd" value="">
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
									<td>${vo.regDate }</td>
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
					    <li><a href="javascript:goPage(${page.firstPageNo})" class="first">처음 페이지</a></li>
					    <li><a href="javascript:goPage(${page.prevPageNo})" class="prev">이전 페이지</a></li>
					    <span>
					        <c:forEach var="i" begin="${page.startPageNo}" end="${page.endPageNo}" step="1">
					            <c:choose>
					                <c:when test="${i eq page.pageNo}">
					                	<li><a href="javascript:goPage(${i})" class="choice">${i}</a></li>
					                </c:when>
					                <c:otherwise>
					                	<li><a href="javascript:goPage(${i})">${i}</a></li>
					                </c:otherwise>
					            </c:choose>
					        </c:forEach>
					    </span>
					    <li><a href="javascript:goPage(${page.nextPageNo})" class="next">다음 페이지</a></li>
					    <li><a href="javascript:goPage(${page.finalPageNo})" class="last">마지막 페이지</a></li>
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