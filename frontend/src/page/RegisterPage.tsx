import Form from "../components/AuthPage/Form";
import { authUserAPI } from "../api/authUser";

export default function RegisterPage() {

    const handleLogin = async (e: React.FormEvent, data: {username?: string, email: string, password: string}) => {
        e.preventDefault();
        try {

            if (!data.username) return;

            await authUserAPI.register({username: data.username, email: data.email, password: data.password});

            alert("Berhasil Coyy")
        } catch (error) {
            console.error(error)
            alert("Gagal Coyy")
        }
    }

  return (
    <>
      <Form typeForm="register" handleType={handleLogin}></Form>
    </>
  )
}
