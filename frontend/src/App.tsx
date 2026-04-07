import { Route, Routes, useNavigate } from 'react-router'
import Dashboard from './page/Dashboard'
import LoginPage from './page/LoginPage'
import RegisterPage from './page/RegisterPage'
import { useEffect } from 'react'
import { useUserStore } from './context/useUserStore'

function App() {
  const isLogin = useUserStore((state) => state.isLogin);
  const navigate = useNavigate();

  useEffect(() => {
    if (isLogin) {
      navigate("/")
    }
  }, [])

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
