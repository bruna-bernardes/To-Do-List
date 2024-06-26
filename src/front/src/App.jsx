import React from 'react';
import { TodoWrapper } from './components/TodoWrapper';
import './App.css';

function App() {
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

export default App;
