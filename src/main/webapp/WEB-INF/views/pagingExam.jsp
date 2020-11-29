<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>


</head>

<body>
					<!-- 2번쨰 줄 시작 -->
					<div class="row">
						<!-- 공지사항 시작 -->
						<div class="col-md-12 stretch-card">
							<div class="card">
								<div class="card-body">
									<p class="card-title">공지사항</p>
									<div class="table-responsive">
										<table id="recent-purchases-listing" class="table">
											<thead>
												<tr>
													<th>#</th>
													<th>제목</th>
													<th>ID</th>
													<th>등록일시</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${list}" var="list">
													<tr>
														<td>${list.postId}</td>
														<td><a href="#" onclick="javascript:location.href='postView.do?recodeNum=${list.postId}'" >${list.postTitle}</td>
														<td>${list.userId}</td>
														<td>${list.postingDate}</td>
													</tr>
												</c:forEach>
														

											</tbody>
										</table>
										<br>
										<br>
										<br>
										<table class="table"><tr><td></td><td>
										<div class="btn-group" role="group"
															aria-label="Basic example">
															<c:if test="${1 != listPageNum}">
															<button type="button" class="btn btn-primary btn-sm" onclick="javascript:location.href='pagingExam.do?pageNum=1';">처음</button>
															<button type="button" class="btn btn-primary btn-sm" onclick="javascript:location.href='pagingExam.do?pageNum=${listPageNum-1}';">이전</button>
															</c:if>
															<c:forEach items="${listPageNumList}" var="page">
																<button type="button" class="btn btn-outline-primary btn-sm" onclick="javascript:location.href='pagingExam.do?pageNum=${page}';">
																		${page}
																</button>
																
															</c:forEach>
															<c:if test="${listPageNumList_lastNum != listPageNum}">
															<button type="button" class="btn btn-primary btn-sm" onclick="javascript:location.href='pagingExam.do?pageNum=${listPageNum+1}';">다음</button>
															<button type="button" class="btn btn-primary btn-sm" onclick="javascript:location.href='pagingExam.do?pageNum=${listPageNumList_lastNum}';">마지막</button>
															</c:if>
										</div>
										</td>
										<td>
                          
										</td>
										</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!-- 공지사항 끝 -->



					</div>


					<!-- 여기까지가 바디의 끝  -->
				</div>
</body>

</html>
