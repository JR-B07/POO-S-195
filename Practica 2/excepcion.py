import tkinter as tk
from tkinter import messagebox

def divide_numbers():
    try:
        dividend = float(entry_dividend.get())
        divisor = float(entry_divisor.get())
        
        if divisor == 0:
            raise ZeroDivisionError
        
        result = dividend / divisor
        messagebox.showinfo("Resultado", f"El resultado de la división es: {result}")
    except ValueError:
        messagebox.showerror("Error", "Por favor ingrese números válidos.")
    except ZeroDivisionError:
        messagebox.showerror("Error", "No se puede dividir por cero.")

root = tk.Tk()
root.title("División de Números")

label_dividend = tk.Label(root, text="Dividendo:")
label_dividend.pack()

entry_dividend = tk.Entry(root)
entry_dividend.pack()

label_divisor = tk.Label(root, text="Divisor:")
label_divisor.pack()

entry_divisor = tk.Entry(root)
entry_divisor.pack()


divide_button = tk.Button(root, text="Dividir", command=divide_numbers)
divide_button.pack()


root.mainloop()
