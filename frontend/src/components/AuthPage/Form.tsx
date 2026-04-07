import { useState } from "react";

interface formProps {
    typeForm: "login" | "register";
    handleType: (e: React.FormEvent, data: {username?: string, email: string, password: string}) => void;
}

export default function Form({typeForm, handleType}: formProps) {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const onSubmitWrapper = (e: React.FormEvent) => {
        handleType(e, {username, email, password})
    }
  
    return (
    <form onSubmit={onSubmitWrapper} >
      {typeForm == "register" ? (
        <div>
          <input type="text" onChange={(event) => setUsername(event.target.value)} required />
          <input type="email" onChange={(event) => setEmail(event.target.value)} required/>
          <input type="password" onChange={(event) => setPassword(event.target.value)} required/>
        </div>
      ) : (
        <div>
          <input type="email" onChange={(event) => setEmail(event.target.value)} required/>
          <input type="password" onChange={(event) => setPassword(event.target.value)} required/>
        </div>
      )}
      <button type="submit">
        {typeForm == "register" ? "Register" : "Login"}
      </button>
    </form>
  );
}
