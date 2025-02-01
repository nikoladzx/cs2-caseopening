import SlotItem from "../SlotItem";

export interface SlotResponse {
    amount: number;
    win: boolean;
    items: SlotItem[];
}