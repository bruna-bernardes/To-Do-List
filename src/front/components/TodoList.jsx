import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faPen } from '@fortawesome/free-solid-svg-icons';

export const TodoList = () => {
  return (
    <div className='TodoList'>
      <div className='Todo'>
        <span className='todo-check'><input type="checkbox"  /></span>
        <p className='todo-text '>Tarefa do usuário 1</p>
        <span className='todo-actions'>
          <FontAwesomeIcon className='edit-icon' icon={faPen} />
          <FontAwesomeIcon className='delete-icon' icon={faTrash} />
        </span>
      </div>
      <div className='Todo'>
        <span className='todo-check'><input type="checkbox"  /></span>
        <p className='todo-text '>Tarefa do usuário 2</p>
        <span className='todo-actions'>
          <FontAwesomeIcon className='edit-icon' icon={faPen} />
          <FontAwesomeIcon className='delete-icon' icon={faTrash} />
        </span>
      </div>
      <div className='Todo'>
        <span className='todo-check'><input type="checkbox"  /></span>
        <p className='todo-text '>Tarefa do usuário 3</p>
        <span className='todo-actions'>
          <FontAwesomeIcon className='edit-icon' icon={faPen} />
          <FontAwesomeIcon className='delete-icon' icon={faTrash} />
        </span>
      </div>
      <div className='Todo'>
        <span className='todo-check'><input type="checkbox" /></span>
        <p className='todo-text'>Tarefa do usuário 4</p>
        <span className='todo-actions'>
          <FontAwesomeIcon className='edit-icon' icon={faPen} />
          <FontAwesomeIcon className='delete-icon' icon={faTrash} />
        </span>
      </div>
    </div>
  );
};
