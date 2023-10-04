<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Image Store ${selectedGroup.name}</title>
  <link rel="stylesheet" href="/ImageStore/libs/semantic-ui/semantic.min.css">
  <link rel="stylesheet" href="/ImageStore/libs/bootstrap/css/bootstrap-utilities.min.css">
  <style><%@include file="./reset.css" %></style>
  <style><%@include file="./styles.css" %></style>
</head>

<body>
	<div id="main-wrapper">
    <section class="group d-flex flex-column">
      <header class="p-2">
        <h4>Folder/</h4>
      </header>
      <main class="flex-grow-1">
        <div class="d-flex flex-wrap">
        	<c:forEach var="group" items="${imageGroups}">
        		<div class="image-folder">
		            <div class="d-flex align-items-center justify-content-between">
		              <a class="text-secondary" href="/ImageStore/home?group-id=${group.id}">
		                <i class="folder open outline icon fs-1${(group.id == selectedGroup.id)? ' text-success':''}"></i>  
		              </a>
		              <div class="ui icon top left pointing dropdown more-control p-2">
		                <i class="ellipsis vertical icon"></i>
		                <div class="menu">              
		                  <button class="item w-100"
		                    data-group-id="${group.id}"
		                    data-group-name="${group.name}"
		                    data-selected-group-id="${selectedGroup.id}"
		                    onclick="showModalEditGroup(this)">
		                    <i class="edit icon"></i>
		                    <span>Edit</span>
		                  </button>
		                  <button class="item w-100"
		                    data-group-id="${group.id}"
		                    data-group-name="${group.name}"
		                    data-selected-group-id="${selectedGroup.id}"
		                    onclick="deleteGroup(this)">
		                    <i class="trash icon"></i>
		                    <span>Delete</span>
		                  </button>
		                  <div class="ui tiny modal edit-group">
		                    <i class="close icon"></i>
		                    <div class="header">
		                      Edit Folder
		                    </div>
		                    <div class="content">
		                      <form class="ui form" 
		                            method="post"
		                            action="/ImageStore/admin/group/update">
		                        <input type="hidden" name="selected-group-id" value="${selectedGroup.id}">
		                        <input type="hidden" name="group-id">
		                        <div class="ui field">
		                          <span>Rename for: <strong class="group-name">...</strong> </span>
		                        </div>
		                        <div class="ui field">
		                          <label>New Name</label>
		                          <input type="text" name="new-name">
		                        </div>
		                        <button class="ui button" type="submit">Update</button>
		                      </form>
		                    </div>
		                  </div>
		                </div>
		              </div>
		            </div>
		            <div>
		              <span>${group.name}</span>
		            </div>
          		</div>	
        	</c:forEach>
          <!-- <div class="image-folder">
            <div class="d-flex align-items-center justify-content-between">
              <a class="ui " href="#">
                <i class="folder open outline icon fs-1"></i>  
              </a>
              <div class="ui icon top left pointing dropdown more-control p-2">
                <i class="ellipsis vertical icon"></i>
                <div class="menu">              
                  <button class="item w-100"
                    data-group-id="1"
                    data-group-name="Group 1"
                    onclick="showModalEditGroup(this)">
                    <i class="edit icon"></i>
                    <span>Edit</span>
                  </button>
                  <button class="item w-100"
                    data-group-id="1"
                    data-group-name="Group 1"
                    data-selected-group-id="1"
                    onclick="deleteGroup(this)">
                    <i class="trash icon"></i>
                    <span>Delete</span>
                  </button>
                  <div class="ui tiny modal edit-group">
                    <i class="close icon"></i>
                    <div class="header">
                      Edit Folder
                    </div>
                    <div class="content">
                      <form class="ui form" 
                            method="POST"
                            action="/ImageStore/admin/group/update">
                        <input type="hidden" name="group-id" value="1">
                        <div class="ui field">
                          <span>Rename for: <strong class="group-name">Folder 1</strong> </span>
                        </div>
                        <div class="ui field">
                          <label>New Name</label>
                          <input type="text" >
                        </div>
                        <button class="ui button">Update</button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div>
              <span>Folder 1 and 2 and 3</span>
            </div>
          </div> -->
        </div>
      </main>
      <footer class="d-flex justify-content-end p-2">
        <button class="ui icon button p-2" onclick="showModalAddGroup()">
          <i class="plus icon"></i>
        </button>
        <div class="ui tiny modal add-group">
          <i class="close icon"></i>
          <div class="header">
            Add Folder
          </div>
          <div class="content">
            <form class="ui form"
                  method="POST"
                  action="/ImageStore/admin/group/add">
              <input type="hidden" name="selected-group-id" value="${selectedGroup.id}">
              <div class="ui field">
                <label>Name</label>
                <input type="text" name="name">
              </div>
              <div class="d-flex justify-content-between">
                <button type="reset" class="ui button">Clear</button>
                <button type="submit" class="ui button primary">Add</button>
              </div>           
            </form>
          </div>
        </div>
      </footer>
    </section>
    <section class="image d-flex flex-column">
        <header class="p-2">
          <h4>${selectedGroup.name += "/"}</h4>
        </header>
        <main class="flex-grow-1">
          <div class="d-flex flex-wrap">
          	<c:forEach var="img" items="${imageFiles}">
          		<div class="image-info">
              <div class="d-flex align-items-center justify-content-between">
                <a target="_blank" href="/ImageStore/image/origin?id=${img.id}">
                  <img src="/ImageStore/image/thumbnail?id=${img.id}"> 
                </a>
                <div class="ui icon top left pointing dropdown more-control p-2">
                  <i class="ellipsis vertical icon"></i>
                  <div class="menu">       
                    <button class="item w-100"
                            data-link="${pageContext.request.serverName}:${pageContext.request.serverPort}/ImageStore/image/origin?id=${img.id}" 
                            onclick="copyLinkToClipboard(this)">
                      <i class="linkify icon"></i>
                      <span>Copy</span>
                    </button>
                    <a href="/ImageStore/image/origin?id=${img.id}" class="item w-100" download="${img.uploadedName}">
                      <i class="download icon"></i>
                      <span>Download</span>
                    </a>
                    <button class="item w-100 " 
                            data-image-id="${img.id}"
                            data-image-name="${img.uploadedName}"
                            data-selected-group-id="${selectedGroup.id}"
                            onclick="showModalEditImage(this)">
                      <i class="edit icon"></i>
                      <span>Edit</span>
                    </button>
                    <button class="item w-100 "
                            data-image-id="${img.id}"
                            data-image-name="${img.uploadedName}"
                            data-selected-group-id="${selectedGroup.id}"
                            onclick="deleteImage(this)">
                      <i class="trash icon"></i>
                      <span>Delete</span>
                    </button>
                    <div class="ui tiny modal edit-image">
                      <i class="close icon"></i>
                      <div class="header">
                        Edit Image
                      </div>
                      <div class="content">
                        <form class="ui form" 
                              method="post" 
                              action="/ImageStore/admin/image/update">
                          <input type="hidden" name="selected-group-id" value="${selectedGroup.id}" >
                          <input type="hidden" name="image-id">
                          <div class="ui field">
                            <span>Rename for: <strong class="image-name">...</strong> </span>
                          </div>
                          <div class="ui field">
                            <label>New Name</label>
                            <input type="text" name="new-name">
                          </div>
                          <div class="d-flex justify-content-between">
                            <button class="ui button" type="reset">Clear</button>
                            <button class="ui button primary" type="submit">Update</button>
                          </div>                      
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>           
              <div class="py-1">
                <span class="text-break">${img.uploadedName}</span>
              </div>
            </div>
          	</c:forEach>
            <!-- <div class="image-info">
              <div class="d-flex align-items-center justify-content-between">
                <a class="" href="#">
                  <img src="../../assets/imgs/bird.png"> 
                </a>
                <div class="ui icon top left pointing dropdown more-control p-2">
                  <i class="ellipsis vertical icon"></i>
                  <div class="menu">
                    <button class="item w-100"
                            data-link="http://localhost/ImageStore/image?id=1"
                            onclick="copyLinkToClipboard(this)">
                      <i class="linkify icon"></i>
                      <span>Copy</span>
                    </button>
                    <a href="#" class="item w-100" download="test.png">
                      <i class="download icon"></i>
                      <span>Download</span>
                    </a>
                    <button class="item w-100 " 
                            data-image-id="1"
                            data-image-name="image1.png"
                            onclick="showModalEditImage(this)">
                      <i class="edit icon"></i>
                      <span>Edit</span>
                    </button>
                    <button class="item w-100 "
                            data-image-id="1"
                            data-image-name="image1.png"
                            data-selected-group-id="1"
                            onclick="deleteImage(this)">
                      <i class="trash icon"></i>
                      <span>Delete</span>
                    </button>
                    <div class="ui tiny modal edit-image">
                      <i class="close icon"></i>
                      <div class="header">
                        Edit Image
                      </div>
                      <div class="content">
                        <form class="ui form" 
                              method="post" 
                              action="/ImageStore/admin/image/update">
                          <input type="hidden" name="image-id" value="1">
                          <div class="ui field">
                            <span>Rename for: <strong class="image-name">ImageXyz.png</strong> </span>
                          </div>
                          <div class="ui field">
                            <label>New Name</label>
                            <input type="text" name="new-name">
                          </div>
                          <div class="d-flex justify-content-between">
                            <button class="ui button" type="reset">Clear</button>
                            <button class="ui button primary" type="submit">Update</button>
                          </div>                      
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>           
              <div class="py-1">
                <span class="text-break">Folderdfsdfsdfsdfssddffdsfsdf1and2andsfasafdsfsfsdf sdf sda safdsaf3</span>
              </div>
            </div> -->
          </div>
        </main>
        <footer class="d-flex justify-content-end p-2">
          <button class="ui icon button p-2" onclick="showModalAddImage()">
            <i class="plus icon"></i>
          </button>
          <div class="ui tiny modal add-image">
            <i class="close icon"></i>
            <div class="header">
              Add Image File
            </div>
            <div class="content">
              <form class="ui form" method="post" 
                    action="/ImageStore/admin/image/add"
                    enctype="multipart/form-data">
                <input type="hidden" name="selected-group-id" value="${selectedGroup.id}">
                <div class="ui field">
                  <label>Images </label>
                  <input type="file" name="image-file" multiple >
                </div>
                <div class="d-flex justify-content-between">
                  <button class="ui button" type="reset">Clear</button>
                  <button class="ui button primary" type="submit">Add</button>
                </div>            
              </form>
            </div>
          </div>
        </footer>
    </section>
  </div>
  <script src="/ImageStore/libs/jquery-3.7.1.min.js"></script>
  <script src="/ImageStore/libs/semantic-ui/semantic.min.js"></script>
  <script><%@ include file="./script.js" %></script>
</body>
</html>