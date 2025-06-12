import {Animation} from "./animation";

export class Serie {
    id: number;
    libelle: string;
    active: boolean;
    animations : Animation[];

    constructor(id: number,libelle: string, active: boolean,animations: Animation[]){
        this.id = id;
        this.libelle = libelle;
        this.active = active;
        this.animations = animations;
    }
}
