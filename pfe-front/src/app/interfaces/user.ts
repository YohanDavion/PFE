export class User {
  id : number;
  login : string;
  // password : string;
  role : string;
  telephone : string;

  constructor(id: number, login : string, /*password: string,*/ role : string, telephone : string){
    this.id = id;
    this.login = login;
    // this.password = adresse;
    this.role = role;
    this.telephone = telephone;
  }
}
