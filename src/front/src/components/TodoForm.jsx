import React, { useState } from 'react';

export const TodoForm = ({ addTodo }) => {
  const [value, setValue] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (value) {
      // Adicionar tarefa
      addTodo(value);
      // Limpar formulário após envio
      setValue('');
    }
  };

  return (
    <form className='TodoForm' onSubmit={handleSubmit}>
      <input
        type='text'
        value={value}
        onChange={(e) => setValue(e.target.value)}
        className='todo-input'
        placeholder='O que você precisa fazer? Descrição da nova tarefa.'
      />
      <button type='submit' className='todo-btn'>
        +
      </button>
    </form>
  );
};
