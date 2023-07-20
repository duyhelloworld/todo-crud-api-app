// Xử lý sự kiện khi người dùng gửi form để thêm todo mới
const addTodoForm = document.getElementById('addTodoForm');

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
            todoDiv.className = `todo-${todo.id}`;
            todoDiv.innerHTML = `
                <input type="checkbox" id="todo-${todo.id}" />  
                <h3><i>${todo.id}</i>${todo.shortName}</h3>
                <p>${todo.description}</p>
                <p>Created At: ${todo.createdAt}</p>
            `;
            todosDiv.appendChild(todoDiv);

            // Reset các trường input sau khi thêm todo thành công
            shortNameInput.value = '';
            descriptionInput.value = '';
        })
        .catch(function (error) {
            console.log(error);
        });
});