import React, { useState, useEffect } from 'react';
import { Modal, Button, Form, Input, Select, DatePicker } from 'antd';
import { v4 as uuidv4 } from 'uuid';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faPen, faPlus } from '@fortawesome/free-solid-svg-icons';
import { TodoForm } from './TodoForm';
import { TodoList } from './TodoList';
import '../App.css';

const { TextArea } = Input;
const { Option } = Select;

export const TodoWrapper = () => {
  const [todos, setTodos] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [currentTask, setCurrentTask] = useState(null);
  const [newTask, setNewTask] = useState({ title: '', description: '', type: '', priority: '', date: '', days: '' });

  useEffect(() => {
    const savedTodos = JSON.parse(localStorage.getItem('todos')) || [];
    setTodos(savedTodos);
  }, []);

  const addTodo = (todo) => {
    const newTodo = { id: uuidv4(), ...todo, completed: false };
    setTodos([...todos, newTodo]);
    localStorage.setItem('todos', JSON.stringify([...todos, newTodo]));
  }

  const updateTodo = (updatedTask) => {
    const updatedTodos = todos.map(todo => 
      todo.id === updatedTask.id ? updatedTask : todo
    );
    setTodos(updatedTodos);
    localStorage.setItem('todos', JSON.stringify(updatedTodos));
  }

  const deleteTodo = (id) => {
    const newTodos = todos.filter((todo) => todo.id !== id);
    setTodos(newTodos);
    localStorage.setItem('todos', JSON.stringify(newTodos));
  }

  const toggleComplete = id => {
    const newTodos = todos.map(todo =>
      todo.id === id ? { ...todo, completed: !todo.completed } : todo
    );
    setTodos(newTodos);
    localStorage.setItem('todos', JSON.stringify(newTodos));
  }

  const handleSubmit = () => {
    if (isEditing) {
      updateTodo(newTask);
    } else {
      addTodo(newTask);
    }
    setNewTask({ title: '', description: '', type: '', priority: '', date: '', days: '' });
    setIsModalOpen(false);
    setIsEditing(false);
  }

  const handleChange = (value, name) => {
    setNewTask({ ...newTask, [name]: value });
  }

  const handleEdit = (task) => {
    setCurrentTask(task);
    setNewTask(task);
    setIsModalOpen(true);
    setIsEditing(true);
  }

  return (
    <div className='TodoWrapper'>
      <button onClick={() => setIsModalOpen(true)} className='add-todo-button'>
        <FontAwesomeIcon icon={faPlus} />
      </button>
      {todos.map((todo) =>
        <TodoList
          key={todo.id}
          task={todo}
          deleteTodo={deleteTodo}
          toggleComplete={toggleComplete}
          onEdit={handleEdit}
        />
      )}
      <Modal
        title={isEditing ? "Editar Tarefa" : "Nova Tarefa"}
        visible={isModalOpen}
        onCancel={() => { setIsModalOpen(false); setIsEditing(false); }}
        onOk={handleSubmit}
      >
        <Form layout="vertical">
          <Form.Item label="Título" required>
            <Input
              name="title"
              value={newTask.title}
              onChange={(e) => handleChange(e.target.value, 'title')}
            />
          </Form.Item>
          <Form.Item label="Descrição" required>
            <TextArea
              name="description"
              value={newTask.description}
              onChange={(e) => handleChange(e.target.value, 'description')}
            />
          </Form.Item>
          <Form.Item label="Tipo" required>
            <Select
              name="type"
              value={newTask.type}
              onChange={(value) => handleChange(value, 'type')}
            >
              <Option value="">Selecione</Option>
              <Option value="data">Data</Option>
              <Option value="prazo">Prazo</Option>
              <Option value="livre">Livre</Option>
            </Select>
          </Form.Item>
          {newTask.type === 'data' && (
            <Form.Item label="Data" required>
              <DatePicker
                name="date"
                value={newTask.date ? moment(newTask.date) : null}
                onChange={(date, dateString) => handleChange(dateString, 'date')}
              />
            </Form.Item>
          )}
          {newTask.type === 'prazo' && (
            <Form.Item label="Dias" required>
              <Input
                type="number"
                name="days"
                value={newTask.days}
                onChange={(e) => handleChange(e.target.value, 'days')}
              />
            </Form.Item>
          )}
          <Form.Item label="Prioridade" required>
            <Select
              name="priority"
              value={newTask.priority}
              onChange={(value) => handleChange(value, 'priority')}
            >
              <Option value="">Selecione</Option>
              <Option value="alta">Alta</Option>
              <Option value="media">Média</Option>
              <Option value="baixa">Baixa</Option>
            </Select>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
}
