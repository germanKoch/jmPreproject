$(document).ready(function () {
    $.ajax("admin/all", {
        dataType: "json",
        success: function (data) {
            data.forEach(function (user) {
                addNewLine(user.id, user.name, user.password, user.roles)
            })
        }
    })


    $('#AddForm').on("submit", function (event) {
        event.preventDefault()
        var name = $('#InputName1').val()
        var password = $('#InputPassword1').val()
        var roles = $('#RoleSelect1').val().toString().split(",")
        $.ajax("admin/add", {
            data: {
                name: name,
                password: password,
                roles: roles
            },
            method: "post",
            success: function (user) {
                $('#all').addClass("show active")
                $('#all-nav').addClass('active')
                $('#new').removeClass("show active")
                $('#new-nav').removeClass("active")

                addNewLine(user.id, user.name, user.password, user.roles)
            }
        })


    })

    $('#ChangeForm').on("submit", function (event) {
        event.preventDefault()
        var id = $('#SendChanges').val()
        var name = $('#InputName').val()
        var password = $('#InputPassword').val()
        var roles = $('#RoleSelect').val().toString().split(",")
        $.ajax("admin/change", {
            data: {
                id: id,
                name: name,
                password: password,
                roles: roles
            },
            method: "post",
            success: function (user) {
                var roles = ""
                user.roles.forEach(function (role) {
                    roles += role.shortName + ' '
                })

                $('.modal').modal('toggle');
                $("#" + id + " td:nth-child(2)").text(user.name)
                $("#" + id + " td:nth-child(3)").text(user.password)
                $("#" + id + " td:nth-child(4)").text(roles)
            }
        })
    })

    $('.userList').on("click", '.delete-button', function (event) {
        var id = $(this).data('id')
        $.ajax("admin/delete", {
            data: {id: id},
            method: "post",
            success: function (data) {
                $('#' + id).remove();
            }
        })
    })

    $('#ModalEdit').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget)
        var id = button.data('id')
        var name = button.data('name')
        var password = button.data('password')
        var roles = button.data('roles')

        var modal = $(this)
        var optional = $('.optional-edit')
        optional.each(function () {
            if (roles.includes($(this).text())) {
                $(this).prop('selected', true);
            }
        });
        modal.find('#InputName').val(name);
        modal.find('#InputPassword').val(password);
        modal.find('#SendChanges').val(id);
    })
});

function addNewLine(id, name, password, roles) {
    var list = $('.userList')
    var line = $('<tr></tr>')
    var rolesStr = ""
    var edit = $('<button></button>')
    var del = $('<button></button>')

    line.attr("id", id)

    roles.forEach(function (role) {
        rolesStr += role.shortName + ' '
    })

    edit.addClass("btn btn-info").text("Edit")
    edit.attr({
        type: "button",
        "data-toggle": "modal",
        "data-target": "#ModalEdit",
        "data-id": id,
        "data-name": name,
        "data-password": password,
        "data-roles": rolesStr,

    })

    del.addClass("btn btn-danger delete-button").text("Delete")
    del.attr({
        "data-id": id
    })

    line.append($('<th></th>').attr("scope", "row").text(id))
    line.append($('<td></td>').text(name))
    line.append($('<td></td>').text(password))
    line.append($('<td></td>').text(rolesStr))
    line.append($('<td></td>').append(edit))
    line.append($('<td></td>').append(del))
    list.append(line)
}