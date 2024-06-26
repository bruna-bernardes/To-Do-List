import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faPen } from '@fortawesome/free-solid-svg-icons';

export const TodoList = ({ task, deleteTodo, toggleComplete, onEdit }) => {
  const handleEditClick = () => {
    onEdit(task); // Chama a função onEdit passando a tarefa atual
  };

  return (
    <div className='Todo'>
      <span className='todo-check'>
        <input
          type="checkbox"
          checked={task.completed}
          onChange={() => toggleComplete(task.id)}
        />
      </span>
      <p className={`todo-text ${task.completed ? 'completed' : 'incompleted'}`}>
        {task.title}
      </p>
      <div className='todo-actions'>
        <FontAwesomeIcon
          className='edit-icon'
          icon={faPen}
          onClick={handleEditClick} // Chama a função para editar
        />
        <FontAwesomeIcon
          className='delete-icon'
          icon={faTrash}
          onClick={() => deleteTodo(task.id)}
        />
      </div>
    </div>
  );
};
