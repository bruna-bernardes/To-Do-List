import React from 'react';
import { TodoWrapper } from './components/TodoWrapper';
import './style.css';

export function App(props) {
  return (
    <div className='App'>
      <div className='header'>
        <h1>To do List</h1>
        <div className='search-bar'>
          <input type='text' placeholder='Buscar ID:' />
          <button className='search-btn'>🔍</button>
        </div>
      </div>
      <TodoWrapper />
    </div>
  );
}

// Log to console
console.log('Hello console');
