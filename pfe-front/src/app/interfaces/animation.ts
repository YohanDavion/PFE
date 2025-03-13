export class Animation {
    id: number;
    contenu: string;
    valide: boolean;

    constructor(id: number,contenu: string,valide: boolean){
        this.id=id;
        this.contenu=contenu;
        this.valide=valide;
    }
}
