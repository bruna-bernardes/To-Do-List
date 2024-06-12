import React from 'react';
import { TodoForm } from './TodoForm';
import { TodoList } from './TodoList';

export const TodoWrapper = () => {
  return (
    <div className='TodoWrapper'>
      <TodoForm />
      <TodoList />
    </div>
  );
}

// Log to console
console.log('Hello console')