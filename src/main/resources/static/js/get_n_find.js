const url = "http://localhost:8080/api/v1/todo"

// Gửi yêu cầu GET để lấy danh sách todo và hiển thị trên giao diện
axios.get(url)
    .then(function (response) {
        var todos = response.data;
        var todosDiv = document.getElementById('todos');

        todos.forEach(function (todo) {
            var todoDiv = document.createElement('div');
            todoDiv.className = `todo-${todo.id}`;
            todoDiv.innerHTML = `
                <input type="checkbox" />
                <h3><i>${todo.id}</i>. ${todo.shortName}</h3>
                <p>- Nội dung : ${todo.description}</p>
                <p>- Lập ra vào : ${todo.createdAt}</p>
            `;
            todosDiv.appendChild(todoDiv);
        });
    })
    .catch(function (error) {
        console.log(error);
    });

// Xử lý sự kiện khi người dùng gửi form để tìm todo
var findTodo = document.getElementById('findTodo');

findTodo.addEventListener('submit', function (event) {
    event.preventDefault();

    var idInput = document.getElementById('id');
    // Gửi yêu cầu GET để lấy todo
    axios.get(url + `/${idInput.value}`)
        .then(function (response) {
            var todo = response.data;
            console.log(response);

            let todosDiv = document.getElementById('todos');
            todosDiv.textContent = "";

            let todoDiv = document.createElement('div');
            todoDiv.className = `todo-${todo.id}`;
            todoDiv.innerHTML = `
                    <input type="checkbox" />
                    <h3><i>${todo.id}</i>${todo.shortName}</h3>
                    <p>${todo.description}</p>
                    <p>Created At: ${todo.createdAt}</p>
                `;
            todosDiv.appendChild(todoDiv);
        })
        .catch(function (error) {
            alert(error["response"]["data"]["reason"])
            console.log(error);
        });
});

