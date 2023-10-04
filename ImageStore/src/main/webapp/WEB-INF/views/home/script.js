$('.dropdown').dropdown({
    label: {
        duration: 0,
    },
    debug: true,
    performance: true,
});

function showModalAddGroup() {
    $('.ui.modal.add-group')
        .modal('show')
    ;
}
function showModalEditGroup(btnEl) {
    let groupId = btnEl.dataset.groupId;
    let groupName = btnEl.dataset.groupName;
	let selectedGroupId = btnEl.dataset.selectedGroupId;
    $('.ui.modal.edit-group input[name="group-id"]').val(groupId);
    $('.ui.modal.edit-group strong.group-name').text(groupName);
	$('.ui.modal.edit-group input[name="selected-group-id"]').val(selectedGroupId);
    $('.ui.modal.edit-group')
        .modal('show')
    ;
}
function showModalEditImage(btnEl) {
    let imageId = btnEl.dataset.imageId;
    let imageName = btnEl.dataset.imageName;
	let selectedGroupId = btnEl.dataset.selectedGroupId;
    $('.ui.modal.edit-image input[name="image-id"]').val(imageId);
    $('.ui.modal.edit-image strong.image-name').text(imageName);
	$('.ui.modal.edit-image input[name="selected-group-id"]').val(selectedGroupId);
    $('.ui.modal.edit-image')
        .modal('show')
    ;
}
function showModalAddImage() {
    $('.ui.modal.add-image')
        .modal('show')
    ;
}
function deleteGroup(btnEl) {
    let groupId = btnEl.dataset.groupId;
    let groupName = btnEl.dataset.groupName;
    let selectedGroupId = btnEl.dataset.selectedGroupId;
    if (confirm("Delete Folder: " + groupName + "?") == true) {
        let options = {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: "group-id=" + groupId
                + "&selected-group-id=" + selectedGroupId
        }
        fetch("/ImageStore/admin/group/delete", options)
            .then(()=> {
                if (groupId == selectedGroupId)
                    window.location.href = "/ImageStore/home";
                else {
                    window.location.href = "/ImageStore/home?group-id=" + selectedGroupId;
                }
            });
    }
}
function deleteImage(btnEl) {
    let imageId = btnEl.dataset.imageId;
    let imageName = btnEl.dataset.imageName;
    let selectedGroupId = btnEl.dataset.selectedGroupId;
    if (confirm("Delete Image: " + imageName + "?") == true) {
        let options = {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: "image-id=" + imageId
                    + "&selected-group-id=" + selectedGroupId
        }
        fetch("/ImageStore/admin/image/delete", options)
            .then(()=> {
                window.location.href = "/ImageStore/home?group-id=" + selectedGroupId;          
            });
    }
}
function copyLinkToClipboard(btnEl) {
    let link = btnEl.dataset.link;
    navigator.clipboard.writeText(link);
}
