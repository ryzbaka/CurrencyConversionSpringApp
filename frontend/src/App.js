import './App.css';
import ButtonAppBar from './AppBar';
import MainInterface from './MainInterface';

function App() {
  return (
    <div className="App">
        <ButtonAppBar/>
        <div className="primary-container">
          <MainInterface/>
        </div>
    </div>
  );
}

export default App;
