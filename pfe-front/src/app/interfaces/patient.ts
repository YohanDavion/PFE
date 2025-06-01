export class Patient {
    id: number;
    nomParent: string;
    prenomParent: string;
    nomPatient: string;
    prenomPatient: string;

    constructor(id: number, nomParent: string, prenomParent: string, nomPatient: string, prenomPatient: string){
        this.id = id;
        this.nomParent = nomParent;
        this.prenomParent = prenomParent;
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
    }
}
