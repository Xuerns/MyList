import Form from "../components/AuthPage/Form";
import { authUserAPI } from "../api/authUser";

export default function RegisterPage() {

    const handleRegister = async (e: React.FormEvent, data: {username?: string, email: string, password: string}) => {
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
    <div className="h-screen flex items-center justify-center">
      <Form typeForm="register" handleType={handleRegister}></Form>
    </div>
  )
}
