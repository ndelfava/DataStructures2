
import matplotlib.pyplot as plt
import numpy as np

# Sample data for a simple line
x = np.array([1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000])
yMax = np.array([1, 3, 4, 8, 8, 11, 13, 15, 17, 18])
yAve = np.array([1, 2.4, 3.1, 5.58, 6.48, 8.68, 9.7, 11.9, 12.9, 14.4])
yThrt = np.array([0,2.32, 3.32, 5.64, 6.64, 8.97, 9.97, 12.29, 13.29, 15.61])

# Basic line plot
plt.plot(x, yMax)
plt.plot(x, yAve)
plt.plot(x, yThrt)

# Customize appearance (optional)
plt.xlabel('n')
plt.ylabel('Operations')
plt.title('Insert time complexity (orange: Ave, blue: Max, green: Theoretical)')

# Show the plot
plt.show()
