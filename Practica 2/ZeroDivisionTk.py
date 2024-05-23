import tkinter as tk
from tkinter import messagebox

class DivisionUI:
    def __init__(self, master):
        self.master = master
        master.title("División App")

        self.label1 = tk.Label(master, text="Dividendo:")
        self.label1.pack()

        self.entry1 = tk.Entry(master)
        self.entry1.pack()

        self.label2 = tk.Label(master, text="Divisor:")
        self.label2.pack()

        self.entry2 = tk.Entry(master)
        self.entry2.pack()

        self.divide_button = tk.Button(master, text="Dividir", command=self.on_divide_button_click)
        self.divide_button.pack()

    def get_dividend(self):
        return float(self.entry1.get())

    def get_divisor(self):
        return float(self.entry2.get())

    def show_result(self, result):
        messagebox.showinfo("Resultado", f"El resultado de la división es: {result}")

    def show_error(self, message):
        messagebox.showerror("Error", message)

    def on_divide_button_click(self):
        pass 
    
