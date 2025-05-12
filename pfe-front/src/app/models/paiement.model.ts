export interface Paiement {
    id: number;
    montant: number;
    datePaiement: Date;
    statut: string;
    modePaiement: string;
    reference: string;
} 