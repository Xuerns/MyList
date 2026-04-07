import { useState } from "react";
import { Link } from "react-router";

interface formProps {
  typeForm: "login" | "register";
  handleType: (
    e: React.FormEvent,
    data: { username?: string; email: string; password: string },
  ) => void;
}

export default function Form({ typeForm, handleType }: formProps) {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const onSubmitWrapper = (e: React.FormEvent) => {
    handleType(e, { username, email, password });
  };

  return (
    <form
      onSubmit={onSubmitWrapper}
      className="w-75 p-5 ring-black/40 ring rounded-xl gap-7 flex flex-col"
    >
      {typeForm === "register" ? (
        <div className="flex flex-col justify-center items-center">
          <h1 className="font-semibold">Ready To Be Productive?</h1>
          <h6 className="text-[10px]">Enter your details to register</h6>
        </div>
      ) : (
        <div className="flex flex-col justify-center items-center">
          <h1 className="font-semibold">Welcome Back!</h1>
          <h6 className="text-[10px]">Pleasec enter your details</h6>
        </div>
      )}
      <div>
        {typeForm === "register" ? (
          <div className="flex flex-col gap-2">
            <div className="flex flex-col">
              <label htmlFor="username" className="text-xs">
                Username:
              </label>
              <input
                className="outline-1 outline-gray-400 focus:outline-gray-700 focus:outline-2 rounded px-2 py-1"
                type="text"
                id="username"
                onChange={(event) => setUsername(event.target.value)}
                required
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="email" className="text-xs">
                Email:
              </label>
              <input
                className="outline-1 outline-gray-400 focus:outline-gray-700 focus:outline-2 rounded px-2 py-1"
                type="email"
                id="email"
                onChange={(event) => setEmail(event.target.value)}
                required
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="password" className="text-xs">
                Password:
              </label>
              <input
                className="outline-1 outline-gray-400 focus:outline-gray-700 focus:outline-2 rounded px-2 py-1"
                type="password"
                id="password"
                onChange={(event) => setPassword(event.target.value)}
                required
              />
            </div>
          </div>
        ) : (
          <div className="flex flex-col gap-2">
            <div className="flex flex-col">
              <label htmlFor="email" className="text-xs">
                Email:
              </label>
              <input
                className="outline-1 outline-gray-400 focus:outline-gray-700 focus:outline-2 rounded px-2 py-1"
                type="email"
                id="email"
                onChange={(event) => setEmail(event.target.value)}
                required
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="password" className="text-xs">
                Password:
              </label>
              <input
                className="outline-1 outline-gray-400 focus:outline-gray-700 focus:outline-2 rounded px-2 py-1"
                type="password"
                id="password"
                onChange={(event) => setPassword(event.target.value)}
                required
              />
            </div>
          </div>
        )}
        <div className="flex items-center justify-end p-2">
          {typeForm === "register" ? (
            <p className="text-[10px]">
              Already have an acount?{" "}
              <Link
                to="/auth/login"
                className="text-blue-600 underline hover:text-blue-800"
              >
                login here
              </Link>
            </p>
          ) : (
            <p className="text-[8px]">
              Dont have an acount yet ?{" "}
              <Link
                to="/auth/register"
                className="text-blue-600 underline hover:text-blue-800"
              >
                register here
              </Link>
            </p>
          )}
        </div>
      </div>

      <button
        type="submit"
        className={`w-full flex items-center justify-center bg-blue-500 active:bg-blue-600 py-2 rounded-lg font-semibold text-white`}
      >
        {typeForm === "register" ? "Register" : "Login"}
      </button>
    </form>
  );
}
