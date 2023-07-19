package com.duyhelloworld.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.duyhelloworld.todoapp.exception.TodoNotFoundException;
import com.duyhelloworld.todoapp.exception.TodoNotMatchConditionException;
import com.duyhelloworld.todoapp.model.Todo;
import com.duyhelloworld.todoapp.model.validator.TodoValidator;
import com.duyhelloworld.todoapp.repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    /**
     * Lấy tất cả Todo hiện tại
     * @param limit : giới hạn todo cần lấy
     * @return findAll() nếu limit == null, 1 phân trang tới limit nếu maxTodo > limit > 0
     * @throws None
     */

    public List<Todo> getTodos(Integer limit) {
        return Optional.ofNullable(limit)
            .map(_limit -> repository.findAll(PageRequest.of(0, _limit)).getContent())
            .orElseGet(() -> repository.findAll());
    }

    /**
     * Lấy todo theo id trong database
     * @param id
     * @return Todo nếu tồn tại
     * @throws TodoNotMatchConditionException id phải > 0
     * @throws TodoNotFoundException Không tồn tại todo có id đó
     */
    public Todo getTodo(Long id) {
        if (id <= 0) {
            throw new TodoNotMatchConditionException("Chỉ số phải là số nguyên dương");
        }
        return repository.findById(id)
                .orElseThrow(() -> 
                    new TodoNotFoundException("Không tồn tại todo có chỉ số " + id));
    }

    /**
     * Thêm 1 todo mới
     * @param todo todo cần thêm vào (id có thể null hoặc bằng 0)
     * @return todo truyền vào nếu lưu thành công
     * @throws IllegalArgumentException Todo gửi lên không thỏa mãn yêu cầu
     */
    public Todo add(Todo todo) {
        if (todo.getId() != null) {
            throw new TodoNotMatchConditionException("Todo gửi lên phải không chứa id");
        }
        TodoValidator.validOrThrow(todo);
        return repository.save(todo);
    }

    /**
     * Cập nhật todo
     * @param id Mã todo cần cập nhật
     * @param todo Thông tin todo cần cập nhật
     * @return Todo nếu cập nhật thành công
     * @throws TodoNotMatchConditionException Các tham số không thỏa mãn yêu cầu
     * @throws IllegalArgumentException Không tồn tại id này
     */
    public Todo updateTodo(Long id, Todo todo) {
        if (id != todo.getId()) {
            throw new TodoNotMatchConditionException("Bất đồng bộ id : id gửi lên phải trùng với id của todo");
        }
        TodoValidator.validOrThrow(todo);

        Optional<Todo> fromDb = repository.findById(id);
        if (!fromDb.isPresent()) {
            throw new TodoNotFoundException("Không tồn tại Todo có giá trị " + id);
        }
        if (fromDb.get().equals(todo)) {
            return todo;
        }
        return repository.save(todo);
    }

    /**
     * Xóa Todo theo id
     * @param id
     * @throws TodoNotMatchConditionException Chỉ số <= 0
     * @throws TodoNotFoundException Không tồn tại Todo có id này
     */
    public void deleteTodo(Long id) {
        if (id <= 0) {
            throw new TodoNotMatchConditionException("Chỉ số (id) phải luôn >= 0");
        }
        Optional<Todo> fromDb = repository.findById(id);
        if (!fromDb.isPresent()) {
            throw new TodoNotFoundException("Không tồn tại Todo có giá trị " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Xóa Todo theo danh sách các id
     * @param ids
     * @throws TodoNotFoundException Không tồn tại giá trị ids
     */
    public void deleteTodo(List<Long> ids) {
        if (ids == null) {
            throw new TodoNotFoundException("Không có id đầu vào để xóa");
        }
        repository.deleteAllById(ids);
    }
}
