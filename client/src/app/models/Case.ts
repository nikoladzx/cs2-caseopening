import { Item } from "./Item";

export interface Case {
    name: string;
    img: File;
    returnedImg: Uint8Array;
    price: number;
    items: Item[];
}
