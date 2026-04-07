import { useNavigate } from "react-router";
import { authUserAPI } from "../api/authUser";
import Form from "../components/AuthPage/Form";
import { useUserStore } from "../context/useUserStore";

export default function LoginPage() {
    const setUsername = useUserStore((state) => state.setUsername);
    const setIsLogin = useUserStore((state) => state.setIsLogin)
    const navigate = useNavigate();

    const handleLogin = async (e: React.FormEvent, data: {email: string, password: string}) => {
        e.preventDefault();
        try {
            const res = await authUserAPI.login({email: data.email, password: data.password})

            localStorage.setItem("token", res.token);

            setUsername(res.username);
            setIsLogin();
          navigate("/")
            alert("Berhasil Coyy")
        } catch (error) {
            console.error(error)
        }
    }

  return (
    <div>
      <Form typeForm="login" handleType={handleLogin}></Form>
    </div>
  )
}
