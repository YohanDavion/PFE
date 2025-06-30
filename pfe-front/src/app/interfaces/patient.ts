import {User} from './user';

export class Patient extends User{
  nom: string;
  prenom: string;
  nomParent: string;
  prenomParent: string;
  adresse: string;
  photo: string;

  constructor(id: number, login : string, role : string, telephone : string, actif : boolean,
              nom: string, prenom: string, nomParent: string, prenomParent:
              string, adresse: string, photo : string){
    super(id, login, role, telephone, actif);
    this.nom = nom;
    this.prenom = prenom;
    this.nomParent = nomParent;
    this.prenomParent = prenomParent;
    this.adresse = adresse;
    this.telephone = telephone;
    this.photo = photo;
  }
}
