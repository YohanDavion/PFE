export class User {
  id : number;
  login : string;
  // password : string;
  role : string;
  telephone : string;
  actif: boolean;

  constructor(id: number, login : string, /*password: string,*/ role : string, telephone : string, actif : boolean){
    this.id = id;
    this.login = login;
    // this.password = adresse;
    this.role = role;
    this.telephone = telephone;
    this.actif = actif;
  }
}
