package com.unifr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.petitjean.TimeSeriesGenerator;

public class Main {

    public static double[][] ReadMatrix(String fileName) throws FileNotFoundException
    {
        Scanner input = new Scanner (new File(fileName));
        // pre-read in the number of rows/columns
        int rows = 0;
        int columns = 0;
        while(input.hasNextLine())
        {
            ++rows;
            String line = input.nextLine();
            if (columns == 0)
            {
                Scanner colReader = new Scanner(line);
                while (colReader.hasNextDouble()) {
                    ++columns;
                    colReader.nextDouble();
                }
            }
        }
        //System.out.println("rows = " + rows + "; cols = " + columns);
        double[][] a = new double[rows][columns];

        input.close();

        // read in the data
        input = new Scanner(new File(fileName));
        for(int i = 0; i < rows; ++i)
        {
            for(int j = 0; j < columns; ++j)
            {
                if(input.hasNextDouble())
                {
                    a[i][j] = input.nextDouble();
                }
            }
        }

        return a;
    }

    public static double[][] Transpose(double[][] orig)
    {
        double[][] trsp = new double[orig[0].length][orig.length];

        for (int i = 0; i < orig.length; i++) {
            for (int j = 0; j < orig[i].length; j++) {
                trsp[j][i] = orig[i][j];
            }
        }

        return trsp;
    }

    public static void PrintMatrix(double[][] a)
    {
        for (double[] row : a) {
            for (double elem : row)
            {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        String fileName = "data/drift10_cut_v2.txt";
        double[][] matrix;

        try
        {
            matrix = ReadMatrix(fileName);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("can't find the file specified: " + fileName);
            System.exit(-1);
            return;
        }

        matrix = Transpose(matrix);

        double[][] newdata = TimeSeriesGenerator.generateFakeData(
                4, matrix, 100, 42);

        PrintMatrix(Transpose(newdata));
    }
}
