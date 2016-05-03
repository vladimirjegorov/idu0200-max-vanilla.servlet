<jsp:useBean id="classroom" scope="request" type="ee.ttu.idu0200.form.ClassroomForm"/>
<%--<jsp:useBean id="classroom" scope="request" type="ee.ttu.idu0200.model.Classroomsroom--%>

<%@include file="/WEB-INF/jsp/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
  <h1>Edit classroom</h1>
  <c:if test="${not empty successMessage}">
    <div class="alert alert-success">
      Update successful.
    </div>
  </c:if>
  <c:url var="formLink" value="/classroom/s?action=save"/>
  <form role="form" class="form-horizontal" action="${formLink}" method="post">
    <div class="form-group">
      <label for="id" class="control-label col-sm-2">Id:</label>
      <div class="col-sm-10">
        <p class="form-control-static" id="id">${classroom.id}</p>
        <input type="hidden" name="id" value="${classroom.id}" />
      </div>
    </div>
    <c:set var="isTitleError" value="${not empty bindingResult['classroom.title']}" />
    <div class="form-group ${isTitleError ? 'has-error' : ''}">
      <label for="title" class="control-label col-sm-2">Title:</label>
      <div class="col-sm-10">
        <input class="form-control" id="title" value="${classroom.title}" name="title">
        <c:if test="${isTitleError}">
          <span class="help-block"><fmt:message key="${bindingResult['classroom.title']}"/></span>
        </c:if>
      </div>
    </div>
    <c:set var="isSeatingCapacityError" value="${not empty bindingResult['classroom.seatingCapacity']}" />
    <div class="form-group ${isSeatingCapacityError ? 'has-error' : ''}">
      <label for="seatingCapacity" class="control-label col-sm-2">Seating capacity:</label>
      <div class="col-sm-10">
        <input class="form-control" id="seatingCapacity" value="${classroom.seatingCapacity}" name="seatingCapacity">
        <c:if test="${isSeatingCapacityError}">
          <span class="help-block"><fmt:message key="${bindingResult['classroom.seatingCapacity']}"/></span>
        </c:if>
      </div>
    </div>
    <c:set var="isDescriptionError" value="${not empty bindingResult['classroom.description']}" />
    <div class="form-group ${isDescriptionError ? 'has-error' : ''}">
      <label for="description" class="control-label col-sm-2">Description:</label>
      <div class="col-sm-10">
        <textarea class="form-control" id="description" name="description">${classroom.description}</textarea>
        <c:if test="${isDescriptionError}">
          <span class="help-block"><fmt:message key="${bindingResult['classroom.description']}"/></span>
        </c:if>
      </div>
    </div>

    <div class="form-group">
      <label class="col-sm-2 control-label"></label>
      <div class="col-sm-10 text-center">
        <button type="submit" class="btn btn-primary ">Submit</button>
        <c:url var="viewAllLink" value="/classroom/s"/>
        <a href="${viewAllLink}" class="btn btn-default">Cancel</a>
      </div>
    </div>

  </form>

</div>
<%@include file="/WEB-INF/jsp/footer.jsp" %>
