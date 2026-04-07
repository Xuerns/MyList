import { Route, Routes } from 'react-router'
import Dashboard from './page/Dashboard'
import LoginPage from './page/LoginPage'
import RegisterPage from './page/RegisterPage'

function App() {
  

  return (
    <>
      <Routes>
        <Route path='/' element={<Dashboard/>}/>
        <Route path='/auth'>
          <Route path='login' element={<LoginPage/>}/>
          <Route path='register' element={<RegisterPage/>}/>
        </Route>
      </Routes>
    </>
  )
}

export default App
