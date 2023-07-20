// Xử lý sự kiện khi người dùng gửi form để thêm todo mới
const updateTodo = document.getElementById('updateTodo');

updateTodo.addEventListener('submit', function (event) {
    event.preventDefault();

    var shortNameInput = document.getElementById('shortName');
    var descriptionInput = document.getElementById('description');
    var currentDateTime = moment().format("YYYY-MM-DD HH:mm:ss");

    var updateTodo = {
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
                <input type="checkbox" />
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