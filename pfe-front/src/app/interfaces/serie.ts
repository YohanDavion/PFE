import { Animation } from "./animation";

export class Serie {
    id: number;
    libelle: string;
    animations : Animation[];

    constructor(id: number,libelle: string,animations: Animation[]){
        this.id = id;
        this.libelle = libelle;
        this.animations = animations;
    }
}
