import {Animation} from "./animation";

export class Serie {
    id: number;
    libelle: string;
    active: boolean;
    statut: string;
    animations : Animation[];

    constructor(id: number,libelle: string, active: boolean, statut : string, animations: Animation[]){
        this.id = id;
        this.libelle = libelle;
        this.active = active;
        this.statut = statut;
        this.animations = animations;
    }
}
