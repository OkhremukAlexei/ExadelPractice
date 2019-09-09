class LoginService {
    static async login(username, password) {
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        };
        const data = {
            'user': username,
            'password': password
        };
        const param = JSON.stringify(data);
        const response = await fetch('/login', {headers: headers,
            method: 'POST', body: param});
        if (!response.ok) {
            throw new Error(response.status.toString());
        }
    }

    static async register(username, password) {
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        };
        const data = {
            'user': username,
            'password': password
        };
        const param = JSON.stringify(data);
        const response = await fetch('/register', {headers: headers,
            method: 'POST', body: param});
        if (!response.ok) {
            throw new Error(response.status.toString());
        }
    }

    static async logout() {
        const response = await fetch('/logout', {method: 'GET'});
        if (!response.ok) {
            throw new Error(response.statusText);
        }
    }
}