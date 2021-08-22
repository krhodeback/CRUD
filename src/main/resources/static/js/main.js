const userService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    findAllUsers: async () => await fetch('http://localhost:8080/crud/admins'),
    findUserById: async id => await fetch(`admins/${id}`).then((response) => response.json()).then((user) => {
        return user
    }),
    addNewUser: async (user) => await fetch('admin/registration', {
        method: 'POST',
        headers: userService.head,
        body: JSON.stringify(user)
    }),
    updateUser: async (user) => await fetch(`admin/updateUser`, {
        method: 'PUT',
        headers: userService.head,
        body: JSON.stringify(user)
    }),
    findRoleByName: async (name) => await fetch(`admin/findRole/${name}`).then((response) => response.json()).then((role) => {
        return role
    }),
    deleteUser: async (id) => await fetch(`admin/delete/${id}`, {method: 'DELETE', headers: userService.head})


}
getTableWithUsers()

async function getTableWithUsers() {
    let table = $('#data')
    table.empty()
    let temp = ''
    userService.findAllUsers()
        .then(res => {
            res.json()
                .then(data => {
                    data.forEach(u => {
                        let userRoles = ''
                        u.roles.forEach(role => {
                            userRoles +=
                                role.name.split('_')[1] + ' '
                        })
                        let tableFilling = `

                            <tr id="oneUserString${u.id}">
                            <td>${u.id}</td>
                            <td>${u.login}</td>
                            <td>${u.email}</td>
                            <td>${u.name}</td>
                            <td> ${userRoles}</td>
                            <td>
                                <button type="button" data-userid=${u.id} data-action="edit" class="btn btn-primary" 
                              onclick="editWindow(${u.id})"  >Edit</button>
                            </td>
                            <td>
                            <button type="button" data-userid=${u.id} data-action="delete" class="btn btn-danger" 
                           onclick="deleteWindow(${u.id})"  >Delete</button>
                            </td>
                            </tr>
                            `
                        temp += tableFilling
                    })
                    table.append(temp)

                })


        })


}

async function editWindow(id) {
    let modalBody = $('.modal-body')
    modalBody.empty()
    let defaultModal = $('#defualtWindow')
    let user = await userService.findUserById(id)
    let modulContent = `
<form id="updateUserForm">
                     <input type="hidden" value=${user.id}
                            id="id" name="userid">
                            
                     <div class="md-form " style="width: 500px">
                         <label for="username">Name</label>
                         <input class="form-control "
                                id="username"
                                name="name"
                                value=${user.name}>
                     </div>
                     <div class="form-group"
                          style="width: 500px">
                         <label for="useremail">Email
                             address</label>
                         <input class="form-control"
                                id="useremail"
                                name="email"
                                value=${user.email}>
                     </div>
                     <div class="form-group"
                          style="width: 500px">
                         <label for="userlogin">Login</label>
                         <input class="form-control"
                                id="userlogin"
                                name="login"
                                value=${user.login}>
                     </div>
                     <div class="form-group"
                          style="width: 500px">
                         <label for="userpassword">Password</label>
                         <input class="form-control"
                                type="password"
                                id="userpassword"
                                name="password"
                                placeholder="Enter password">
                     </div>
                     <select id="userrole" 
                             name="role" 
                             class="form-select"
                             style="width: 500px ; height: 100px;"
                             multiple
                             aria-label="multiple select example">
                         <option value="ROLE_ADMIN">Admin
                         </option>
                         <option value="ROLE_USER">User</option>
                     </select>
                     <div class="d-flex"
                          style="float: right;float: bottom">
                         <button type="button"
                                 class="btn btn-secondary"
                                 data-dismiss="modal">Close
                         </button>
                         <button type="button"
                                 class="btn btn-primary" onclick="updateButton()">Edit
                         </button>
                     </div>
</form>`
    modalBody.append(modulContent)
    defaultModal.modal('show')


}

async function updateButton() {
    let form = $('#updateUserForm')
    let userInfo = $(form).serializeArray()
    let roles = []
    for (let i = 5, j = 0; i < userInfo.length; i++, j++) {
        roles[j] = await userService.findRoleByName(userInfo[i].value)
    }
    let data = {

        id: userInfo[0].value,
        name: userInfo[1].value,
        email: userInfo[2].value,
        login: userInfo[3].value,
        password: userInfo[4].value,
        roles: roles
    }
    if (data.roles.length > 0) {
        userService.updateUser(data)
    } else {
        alert('User must be with role')
        return
    }
    setTimeout(`updateOneString(${data.id})`, 500)
    $('#defualtWindow').modal('hide')
}

async function updateOneString(id) {
    let table = $('#oneUserString' + id)
    table.empty()
    let u = await userService.findUserById(id)
    let userRoles = ''
    u.roles.forEach(role => {
        userRoles +=
            role.name.split('_')[1] + ' '
    })
    let temp = `  <td>${u.id}</td>
                  <td>${u.login}</td>
                  <td>${u.email}</td>
                  <td>${u.name}</td>
                  <td> ${userRoles}</td>
                  <td>
                  <button type="button" data-userid=${u.id} data-action="edit" class="btn btn-primary" 
                   onclick="editWindow(${u.id})"  >Edit</button>
                  </td>
                  <td>
                  <button type="button" data-userid=${u.id} data-action="delete" class="btn btn-danger" 
                  onclick="deleteWindow(${u.id})"  >Delete</button>
                  </td>`
    table.append(temp)
}

async function deleteWindow(id) {
    let modalBody = $('.modal-body')
    modalBody.empty()
    let defaultModal = $('#defualtWindow')
    let user = await userService.findUserById(id)
    let modulContent = `
<form id="deleteUserForm">
                     <input type="hidden" value=${user.id}
                            id="id" name="userid">
                            
                     <div class="md-form " style="width: 500px">
                         <label for="username">Name</label>
                         <input class="form-control "
                                id="username"
                                name="name"
                                value=${user.name} readonly>
                     </div>
                     <div class="form-group"
                          style="width: 500px">
                         <label for="useremail">Email
                             address</label>
                         <input class="form-control"
                                id="useremail"
                                name="email"
                                value=${user.email} readonly >
                     </div>
                     <div class="form-group"
                          style="width: 500px">
                         <label for="userlogin">Login</label>
                         <input class="form-control"
                                id="userlogin"
                                name="login"
                                value=${user.login} readonly>
                     </div>
                     <div class="form-group"
                          style="width: 500px">
                         <label for="userpassword">Password</label>
                         <input class="form-control"
                                type="password"
                                id="userpassword"
                                name="password"
                                placeholder="Enter password" readonly>
                     </div>
                     <select id="userrole" 
                             name="role" 
                             class="form-select"
                             style="width: 500px ; height: 100px;" disabled="true"
                             multiple
                             aria-label="multiple select example">
                         <option value="ROLE_ADMIN">Admin
                         </option>
                         <option value="ROLE_USER">User</option>
                     </select>
                     <div class="d-flex"
                          style="float: right;float: bottom">
                         <button type="button"
                                 class="btn btn-secondary"
                                 data-dismiss="modal">Close
                         </button>
                         <button type="button"
                                 class="btn btn-danger" onclick="deleteButton()">Delete
                         </button>
                     </div>
</form>`
    modalBody.append(modulContent)
    defaultModal.modal('show')
}

async function deleteButton() {
    let form = $('#deleteUserForm')
    let userInfo = $(form).serializeArray()
    let id = userInfo[0].value
    userService.deleteUser(id)
    setTimeout(`deleteTableString(${id})`, 500)
    $('#defualtWindow').modal('hide')
}

async function deleteTableString(id) {
    let table = $('#oneUserString' + id)
    table.empty()
}

async function addNewUser() {
    console.log('add new user')
    let form = $('#newUserForm')
    let userInfo = $(form).serializeArray()
    let roles = []
    for (let i = 4, j = 0; i < userInfo.length; i++, j++) {
        roles[j] = await userService.findRoleByName(userInfo[i].value)
    }
    console.log(userInfo)
    let data = {
        name: userInfo[0].value,
        email: userInfo[1].value,
        login: userInfo[2].value,
        password: userInfo[3].value,
        roles: roles
    }
    console.log(data)
    if (data.roles.length > 0) {
        userService.addNewUser(data)
    } else {
        alert('User must be with role')
    }
    setTimeout(`getTableWithUsers()`, 500)

}





