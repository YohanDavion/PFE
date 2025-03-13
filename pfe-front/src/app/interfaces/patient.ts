export class Patient {
    nomParent: string;
    prenomParent: string;
    nomPatient: string;
    prenomPatient: string;

    constructor(nomParent: string,prenomParent: string,nomPatient: string,prenomPatient: string){
        this.nomParent = nomParent;
        this.prenomParent = prenomParent;
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
    }
}
