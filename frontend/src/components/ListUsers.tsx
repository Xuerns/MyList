import { useEffect, useState } from "react"
import { listUsers } from "../service/UsersService";

interface userState {
    id: number;
    name: string;
    email: string; 
}

export default function ListUsers() {
    const [list, setList] = useState<userState[]>([]);

    useEffect(() => {
        listUsers().then((response) => {
            setList(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

    return (
    <div>
      <ul>
        {list.map(users => (
            <li key={users.id}>
                <span>{users.name}</span>
                <span>{users.email}</span>
            </li>
        ))}
      </ul>
    </div>
  )
}
