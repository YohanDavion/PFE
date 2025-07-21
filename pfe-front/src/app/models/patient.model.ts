export interface Patient {
    id: number;
    nom: string;
    prenom: string;
    email: string;
    telephone: string;
    dateNaissance: Date;
    adresse: string;
    abonnementStatut?: string;
} 