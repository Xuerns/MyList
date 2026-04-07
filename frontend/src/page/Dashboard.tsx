import { useNavigate } from "react-router";
import { useUserStore } from "../context/useUserStore";
import { useEffect } from "react";

export default function Dashboard() {
  const username = useUserStore((state) => state.username);
  const setIsLogin = useUserStore((state) => state.setIsLogin);
  const navigate = useNavigate();

  // Handle for logout
  const handleLogout = () => {
    localStorage.removeItem("token");
    setIsLogin(false);
    navigate("/auth/login");
  };

  // when open web check if token is still there
  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      navigate("/auth/login");
      setIsLogin(false);
    }
  }, []);

  return (
    <div>
      halloo {username}
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}
