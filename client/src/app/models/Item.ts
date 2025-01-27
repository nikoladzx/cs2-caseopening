export interface Item {
    id: number;
    img: File;
    returnedImg: Uint8Array;
    name: string;
    price: number;
    type: string;
    caseId: number;
}