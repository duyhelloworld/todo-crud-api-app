const url = "http://localhost:8080/api/v1/todo"

// Gửi yêu cầu GET để lấy danh sách todo và hiển thị trên giao diện
axios.get(url)
    .then(function (response) {
        var todos = response.data;
        var todosDiv = document.getElementById('todos');

        todos.forEach(function (todo) {
            var todoDiv = document.createElement('div');
            todoDiv.innerHTML = `
                <h3>${todo.shortName}</h3>
                <p>${todo.description}</p>
                <p>Created At: ${todo.createdAt}</p>
            `;

            todosDiv.appendChild(todoDiv);
        });
    })
    .catch(function (error) {
        console.log(error);
    });

// Xử lý sự kiện khi người dùng gửi form để thêm todo mới
var addTodoForm = document.getElementById('addTodoForm');

addTodoForm.addEventListener('submit', function (event) {
    event.preventDefault();

    var shortNameInput = document.getElementById('shortName');
    var descriptionInput = document.getElementById('description');
    var currentDateTime = moment().format("YYYY-MM-DD HH:mm:ss");


    var newTodo = {
        shortName: shortNameInput.value,
        description: descriptionInput.value,
        createdAt: String(currentDateTime)
    };

    // Gửi yêu cầu POST để thêm todo mới
    axios.post(url, newTodo)
        .then(function (response) {
            // Nếu thành công, thêm todo mới vào danh sách hiển thị
            var todo = response.data;
            var todosDiv = document.getElementById('todos');

            var todoDiv = document.createElement('div');
            todoDiv.innerHTML = `
                <h3>${todo.shortName}</h3>
                <p>${todo.description}</p>
                <p>Created At: ${todo.createdAt}</p>
            `;

            todosDiv.appendChild(todoDiv);

            // Reset các trường input sau khi thêm todo thành công
            shortNameInput.value = '';
            descriptionInput.value = '';
            createdAtInput.value = '';
        })
        .catch(function (error) {
            console.log(error);
        });
});